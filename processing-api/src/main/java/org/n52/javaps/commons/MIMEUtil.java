/**
 * ﻿Copyright (C) 2006 - 2014 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.javaps.commons;

/**
 *
 * @author tkunicki
 */
public class MIMEUtil {

    public static String getSuffixFromMIMEType(String mimeType) {
        String[] mimeTypeSplit = mimeType.split("/");
        String suffix = mimeTypeSplit[mimeTypeSplit.length - 1];
        if ("geotiff".equalsIgnoreCase(suffix) || "x-geotiff".equalsIgnoreCase(suffix)) {
            suffix = "tiff";
        } else if ("netcdf".equalsIgnoreCase(suffix) || "x-netcdf".equalsIgnoreCase(suffix)) {
            suffix = "nc";
        } else if ("x-zipped-shp".equalsIgnoreCase(suffix)) {
            suffix = "zip";
        } else if ("text/plain".equals(mimeType)) {
            suffix = "txt";
        } else if ("text/html".equals(mimeType)) {
            suffix = "html";
        } else if ("application/json".equals(mimeType)) {
            suffix = "json";
        } else if ("text/csv".equals(mimeType)) {
            suffix = "csv";
        } else if (mimeType.contains("rData")) {
            suffix = "rData";
        }
        return suffix;
    }
}