/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package cn.com.prescription.framework.data.s2dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.seasar.dao.impl.AbstractMapResultSetHandler;
import org.seasar.extension.jdbc.PropertyType;
import org.seasar.framework.log.Logger;

public class MapResultSetHandler extends AbstractMapResultSetHandler {

    private static final Logger logger = Logger.getLogger(MapResultSetHandler.class);

    public MapResultSetHandler() {
    }

    /**
     * @see org.seasar.extension.jdbc.ResultSetHandler#handle(java.sql.ResultSet)
     */
    public Object handle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            PropertyType[] propertyTypes = createPropertyTypes(resultSet.getMetaData());
            Object row = createRow(resultSet, propertyTypes);
            if (resultSet.next()) {
                // logger.log("WDAO0003", null);
            }
            return row;
        }
        return null;
    }
}