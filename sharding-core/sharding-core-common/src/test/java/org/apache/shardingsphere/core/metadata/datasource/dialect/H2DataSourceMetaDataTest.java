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

package org.apache.shardingsphere.core.metadata.datasource.dialect;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.apache.shardingsphere.core.metadata.datasource.exception.UnrecognizedDatabaseURLException;
import org.apache.shardingsphere.spi.database.DataSourceInfo;
import org.junit.Test;

public final class H2DataSourceMetaDataTest {
    
    @Test
    public void assertDataSourceInfoParam() {
        H2DataSourceMetaData actual = new H2DataSourceMetaData(new DataSourceInfo("jdbc:h2:mem:ds_0;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL", "test"));
        assertThat(actual.getHostName(), is(""));
        assertThat(actual.getPort(), is(-1));
        assertThat(actual.getCatalog(), is("ds_0"));
        assertEquals(actual.getSchemaName(), null);
    }
    
    @Test
    public void assertGetPropertiesWithMem() {
        H2DataSourceMetaData actual = new H2DataSourceMetaData(new DataSourceInfo("jdbc:h2:mem:ds_0;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL", "test"));
        assertThat(actual.getHostName(), is(""));
        assertThat(actual.getPort(), is(-1));
        assertEquals(actual.getSchemaName(), null);
    }
    
    @Test
    public void assertGetPropertiesWithSymbol() {
        H2DataSourceMetaData actual = new H2DataSourceMetaData(new DataSourceInfo("jdbc:h2:~:ds-0;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false;MODE=MySQL", "test"));
        assertThat(actual.getHostName(), is(""));
        assertThat(actual.getPort(), is(-1));
        assertEquals(actual.getSchemaName(), null);
    }
    
    @Test(expected = UnrecognizedDatabaseURLException.class)
    public void assertGetPropertiesFailure() {
        DataSourceInfo dataSourceInfo = new DataSourceInfo("jdbc:h2:file:/data/sample", "test");
        new H2DataSourceMetaData(dataSourceInfo);
    }
}
