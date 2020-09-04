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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.n52.javaps.algorithm.annotation.Algorithm;
import org.n52.javaps.algorithm.annotation.AnnotatedAlgorithmMetadataTest.TestEnum;
import org.n52.javaps.algorithm.annotation.AnnotatedAlgorithmMetadataTest.TestIData;
import org.n52.javaps.algorithm.annotation.BoundingBoxInput;
import org.n52.javaps.algorithm.annotation.BoundingBoxOutput;
import org.n52.javaps.algorithm.annotation.ComplexInput;
import org.n52.javaps.algorithm.annotation.ComplexOutput;
import org.n52.javaps.algorithm.annotation.Execute;
import org.n52.javaps.algorithm.annotation.LiteralInput;
import org.n52.javaps.algorithm.annotation.LiteralOutput;
import org.n52.javaps.engine.Engine;
import org.n52.javaps.engine.EngineException;
import org.n52.javaps.engine.InputDecodingException;
import org.n52.javaps.engine.ProcessNotFoundException;
import org.n52.javaps.engine.ResultPersistence;
import org.n52.javaps.engine.impl.FileBasedResultPersistence;
import org.n52.javaps.test.AbstractTestCase;
import org.n52.shetland.ogc.ows.OwsBoundingBox;
import org.n52.shetland.ogc.ows.OwsCode;
import org.n52.shetland.ogc.wps.Format;
import org.n52.shetland.ogc.wps.JobId;
import org.n52.shetland.ogc.wps.OutputDefinition;
import org.n52.shetland.ogc.wps.ResponseMode;
import org.n52.shetland.ogc.wps.Result;
import org.n52.shetland.ogc.wps.data.ProcessData;
import org.n52.shetland.ogc.wps.data.impl.StringValueProcessData;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {InputProviderList.class, OutputProviderList.class, LocalAlgorithmRepository.class, ProviderAwareListableBeanFactory.class, InputHandlerRepositoryImpl.class, OutputHandlerRepositoryImpl.class, LiteralTypeRepositoryImpl.class, SpringContext.class, TestAlgorithm2.class, ContextAlgorithmRegistrator.class})
public class LocalAlgorithmRepositoryTest extends AbstractTestCase {
            
    public static final String epsg4328String = "EPSG:4328";

//    @Inject
//    private LocalAlgorithmRepository lar;

    @Inject
    private Engine engine;
    
    @Inject
    private ResultPersistence persistence;

    @Before
    public void prepare() {
        if(persistence instanceof FileBasedResultPersistence) {
            ((FileBasedResultPersistence)persistence).setBasePath(new File(System.getProperty("java.io.tmpdir")));
        }
    }

    @Test
    public void testExecuteAlgorithmAddedByIAlgorithmInterfaceIdSameAsCanonicalClassName() {
        
        executeProcess(new OwsCode(TestAlgorithm2.class.getCanonicalName()));
    }

    @Test
    public void testExecuteAlgorithmAddedByIAlgorithmInterfaceIdCustomId() {
        
        executeProcess(TestAlgorithmCustomId.ID);
    }

    @Test
    public void testExecuteAnnotatedAlgorithmAddedByIAlgorithmInterfaceIdSameAsCanonicalClassName() {
        
        executeProcess(new OwsCode(AnnotatedTestAlgorithmIdSameAsClassName.class.getCanonicalName()));
    }

    @Test
    public void testExecuteAnnotatedAlgorithmAddedByIAlgorithmInterfaceIdCustomId() {
        
        executeProcess(new OwsCode(AnnotatedTestAlgorithmCustomId.ID));
    }
    
    private void executeProcess(OwsCode id) {

        JobId jobId1 = null;
        
        List<ProcessData> inputs = new ArrayList<>();
        
        String literalInputValue = "0.05";        
        
        StringValueProcessData literalData = new StringValueProcessData(TestAlgorithm2.LITERALINPUT_ID, new Format("text/plain"), literalInputValue );
        
        inputs.add(literalData);
        
        List<OutputDefinition> outputs = new ArrayList<>();
        
        OutputDefinition outputDefinition = new OutputDefinition(TestAlgorithm2.LITERALOUTPUT_ID, new Format("text/plain"));
        
        outputs.add(outputDefinition);
        
        try {
            jobId1 = engine.execute(id, inputs, outputs, ResponseMode.DOCUMENT);
        } catch (ProcessNotFoundException | InputDecodingException e) {
            fail(e.getMessage());
        }
        
        try {
            Result result = engine.getResult(jobId1).get();
            
            InputStream inputStream = result.getOutputs().get(0).asValue().getData();
            
            StringWriter writer = new StringWriter();
            String encoding = StandardCharsets.UTF_8.name();
            IOUtils.copy(inputStream, writer, encoding);
            String outputString = writer.toString();
            
            assertTrue(literalInputValue.equals(outputString));            
            
        } catch (InterruptedException | ExecutionException | EngineException | IOException e) {
            fail(e.getMessage());
        }
        
    }

}
