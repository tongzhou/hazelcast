/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.executor;

import com.hazelcast.core.MemberLeftException;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.spi.InvocationAction;

import java.util.concurrent.Callable;

/**
 * @mdogan 1/18/13
 */
public class MemberCallableTaskOperation<V> extends CallableTaskOperation<V> implements IdentifiedDataSerializable {


    public MemberCallableTaskOperation() {
    }

    public MemberCallableTaskOperation(String name, Callable<V> callable) {
        super(name, callable);
    }

    @Override
    public InvocationAction onException(Throwable throwable) {
        if (throwable instanceof MemberLeftException) {
            return InvocationAction.THROW_EXCEPTION;
        }
        return super.onException(throwable);
    }

    public int getId() {
        return DataSerializerExecutorHook.MEMBER_CALLABLE_TASK;
    }
}