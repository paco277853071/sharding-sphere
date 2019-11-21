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

package org.apache.shardingsphere.core.metadata.datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.shardingsphere.core.database.DatabaseTypes;
import org.apache.shardingsphere.spi.database.DataSourceInfo;
import org.junit.Before;
import org.junit.Test;

public final class ShardingSphereDataSourceMetaDataTest {
    
    private DataSourceMetas dataSourceMetas;
    
    @Before
    public void setUp() {
        final Map<String, DataSourceInfo> dataSourceInfoMap = new HashMap<String, DataSourceInfo>();
        DataSourceInfo info0 = new DataSourceInfo("jdbc:mysql://127.0.0.1:3306/db_0", "test");
        
        DataSourceInfo info1 = new DataSourceInfo("jdbc:mysql://127.0.0.1:3306/db_1", "test");
        
        dataSourceInfoMap.put("ds_0", info0);
        dataSourceInfoMap.put("ds_1", info1);
        dataSourceMetas = new DataSourceMetas(DatabaseTypes.getActualDatabaseType("MySQL"), dataSourceInfoMap);
    }

    @Test
    public void assertGetAllInstanceDataSourceNamesForShardingRule() {
        assertNotNull(dataSourceMetas.getAllInstanceDataSourceNames());
    }
    
    @Test
    public void assertGetActualCatalogForShardingRule() {
        assertEquals(dataSourceMetas.getDataSourceMetaData("ds_0").getCatalog(), "db_0");
    }
    
    @Test
    public void assertGetActualSchemaNameForShardingRuleForMysql() {
        assertEquals(dataSourceMetas.getDataSourceMetaData("ds_0").getSchemaName(), null);
    }
    
}
