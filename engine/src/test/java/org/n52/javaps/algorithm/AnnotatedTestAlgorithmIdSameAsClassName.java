/*
 * Copyright 2016-2020 52°North Initiative for Geospatial Open Source
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
package org.n52.javaps.algorithm;

import java.util.List;

import org.n52.javaps.algorithm.annotation.Algorithm;
import org.n52.javaps.algorithm.annotation.Execute;
import org.n52.javaps.algorithm.annotation.LiteralInput;
import org.n52.javaps.algorithm.annotation.LiteralOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Algorithm(
        version = "1.0.0")
public class AnnotatedTestAlgorithmIdSameAsClassName {

    private static final Logger log = LoggerFactory.getLogger(AnnotatedTestAlgorithmIdSameAsClassName.class);

    private List<String> literalInput;

    private String literalOutput;

    private int duration;

    @Execute
    public void echo() {

        if (literalInput != null && literalInput.size() > 0) {
            literalOutput = literalInput.get(0);
        } else {
            log.debug("No literal input");
        }

        if (duration != 0) {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                log.error("Could not sleep for: " + duration, e);
            }
        }
    }

    @LiteralOutput(
            identifier = "literalOutput")
    public String getLiteralOutput() {
        return literalOutput;
    }

    @LiteralInput(
            identifier = "literalInput",
            minOccurs = 0,
            maxOccurs = 1)
    public void setLiteralInput(List<String> literalInput) {
        this.literalInput = literalInput;
    }

    @LiteralInput(
            identifier = "duration",
            minOccurs = 0,
            maxOccurs = 1)
    public void setLiteralInput(int duration) {
        this.duration = duration;
    }

}
