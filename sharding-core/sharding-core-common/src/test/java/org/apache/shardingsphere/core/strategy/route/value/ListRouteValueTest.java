/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.core.strategy.route.value;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ListRouteValueTest {
    
    @Test
    public void assertToStringWithEqual() {
        assertThat(new ListRouteValue("order_id", "t_order", Collections.singleton(10)).toString(), is("t_order.order_id = 10"));
    }
    
    @Test
    public void assertToStringWithIn() {
        assertThat(new ListRouteValue("order_id", "t_order", Arrays.asList(10, 20)).toString(), is("t_order.order_id in (10,20)"));
    }
}
