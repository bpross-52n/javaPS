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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.n52.javaps.description.TypedProcessDescription;
import org.n52.javaps.description.impl.TypedProcessDescriptionFactory;
import org.n52.javaps.engine.ProcessExecutionContext;
import org.n52.javaps.io.Data;
import org.n52.javaps.io.literal.xsd.LiteralDoubleType;
import org.n52.javaps.io.literal.xsd.LiteralIntegerType;
import org.n52.shetland.ogc.ows.OwsAnyValue;
import org.n52.shetland.ogc.ows.OwsCode;
import org.n52.shetland.ogc.wps.description.ProcessInputDescription;
import org.n52.shetland.ogc.wps.description.ProcessOutputDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAlgorithm3 extends AbstractAlgorithm {

    private static final Logger log = LoggerFactory.getLogger(TestAlgorithm3.class);

//    public static final OwsCode ID = new OwsCode("org.n52.javaps.algorithm.TestAlgorithm2");
    public static final OwsCode LITERALINPUT_ID = new OwsCode("literalInput");
    public static final OwsCode LITERALINPUT_DURATION_ID = new OwsCode("duration");
    public static final OwsCode LITERALOUTPUT_ID = new OwsCode("literalOutput");
    private TypedProcessDescriptionFactory descriptionFactory = new TypedProcessDescriptionFactory();

    @Override
    public void execute(ProcessExecutionContext context) throws ExecutionException {

        Data<?> data = context.getInputs().get(LITERALINPUT_ID).get(0);

        Data<?> durationData = context.getInputs().get(LITERALINPUT_DURATION_ID) != null ? context.getInputs().get(LITERALINPUT_DURATION_ID).get(0) : null;

        int duration = 0;

        if(durationData != null) {
            duration = ((BigInteger) durationData.getPayload()).intValue();
        }

        if (duration != 0) {
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                log.error("Could not sleep for: " + duration, e);
            }
        }

        context.getOutputs().put(LITERALOUTPUT_ID, data);

    }

    @Override
    protected TypedProcessDescription createDescription() {
        List<ProcessInputDescription> inputs = new ArrayList<>();

        ProcessInputDescription literalInput = descriptionFactory.literalInput().withIdentifier(LITERALINPUT_ID).withType(new LiteralDoubleType()).withDefaultLiteralDataDomain(descriptionFactory.literalDataDomain().withValueDescription(OwsAnyValue.instance())).build();
        ProcessInputDescription literalInputDuration = descriptionFactory.literalInput().withIdentifier(LITERALINPUT_DURATION_ID).withType(new LiteralIntegerType()).withDefaultLiteralDataDomain(descriptionFactory.literalDataDomain().withValueDescription(OwsAnyValue.instance())).build();

        inputs.add(literalInput);
        inputs.add(literalInputDuration);

        List<ProcessOutputDescription> outputs = new ArrayList<>();

        ProcessOutputDescription literalOutput = descriptionFactory.literalOutput().withIdentifier(LITERALOUTPUT_ID).withType(new LiteralDoubleType()).withDefaultLiteralDataDomain(descriptionFactory.literalDataDomain().withValueDescription(OwsAnyValue.instance())).build();

        outputs.add(literalOutput);

        return new TypedProcessDescriptionFactory().process().withIdentifier(getClass().getCanonicalName()).withVersion("1.0.0").withInput(inputs).withOutput(outputs).build();
    }

}
