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

package org.apache.shardingsphere.sql.parser.sql.segment.dml.item;

import org.apache.shardingsphere.sql.parser.core.constant.AggregationType;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class SelectItemsSegmentTest {
    
    @Test
    public void assertFindSelectItemSegments() {
        SelectItemsSegment selectItemsSegment = new SelectItemsSegment(0, 0, false);
        SelectItemSegment selectItemSegment1 = new ExpressionSelectItemSegment(0, 0, "id");
        selectItemsSegment.getSelectItems().add(selectItemSegment1);
        SelectItemSegment selectItemSegment2 = new AggregationSelectItemSegment(0, 0, "count(*)", AggregationType.COUNT, 0);
        selectItemsSegment.getSelectItems().add(selectItemSegment2);
        Collection<AggregationSelectItemSegment> actual = selectItemsSegment.findSelectItemSegments(AggregationSelectItemSegment.class);
        assertThat(actual.size(), is(1));
        assertThat(actual.iterator().next(), is(selectItemSegment2));
    }
}
