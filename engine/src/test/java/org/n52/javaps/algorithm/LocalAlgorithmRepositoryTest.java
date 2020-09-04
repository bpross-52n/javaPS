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

import javax.inject.Inject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.n52.iceland.config.spring.ProviderAwareListableBeanFactory;
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
import org.n52.javaps.engine.InputDecodingException;
import org.n52.javaps.engine.ProcessNotFoundException;
import org.n52.javaps.io.InputHandlerRepositoryImpl;
import org.n52.javaps.io.OutputHandlerRepositoryImpl;
import org.n52.javaps.io.literal.LiteralTypeRepositoryImpl;
import org.n52.javaps.test.AbstractTestCase;
import org.n52.javaps.utils.SpringContext;
import org.n52.shetland.ogc.ows.OwsBoundingBox;
import org.n52.shetland.ogc.ows.OwsCode;
import org.n52.shetland.ogc.wps.OutputDefinition;
import org.n52.shetland.ogc.wps.ResponseMode;
import org.n52.shetland.ogc.wps.data.ProcessData;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {InputProviderList.class, OutputProviderList.class, LocalAlgorithmRepository.class, ProviderAwareListableBeanFactory.class, InputHandlerRepositoryImpl.class, OutputHandlerRepositoryImpl.class, LiteralTypeRepositoryImpl.class, SpringContext.class, TestAlgorithm2.class, ContextAlgorithmRegistrator.class})
public class LocalAlgorithmRepositoryTest extends AbstractTestCase {
        
    public static final String epsg4328String = "EPSG:4328";
 
//    @Inject
//    private AutowireCapableBeanFactory beanFactory;
    
    @Inject
    private LocalAlgorithmRepository lar;
    
    @Inject
    private Engine engine;
    
//    @Inject
//    private BoundingBoxInputOutputHandler handler1;

//    @BeforeClass
//    public static void applySpringIntegration() {
//       new ProviderAwareListableBeanFactory();
//    }

    
//    @Before
//    public void prepare() {
//        MockitoAnnotations.initMocks(this);
//    }
    
    @Test
    public void testLAR() {
        System.out.println(this.lar.getAlgorithmNames());
        
        List<ProcessData> inputs = null;
        List<OutputDefinition> outputs = null;
        try {
            OwsCode id = this.lar.getAlgorithmNames().iterator().next();
            engine.execute(id, inputs, outputs, ResponseMode.DOCUMENT);
        } catch (ProcessNotFoundException | InputDecodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Algorithm(title = "Test Process",
            abstrakt = "Test Abstract",
            version = "1.0.0")
 public static class TestProcess {

     @BoundingBoxInput(identifier= "bboxInput", supportedCRSStringArray = {epsg4328String})
     public OwsBoundingBox bboxInput1;

     @LiteralInput(identifier = "input5",
                   abstrakt = "input5 abstract",
                   title = "input5 title",
                   minOccurs = 1,
                   maxOccurs = 1,
                   defaultValue = "A",
                   uom = "m")
     public TestEnum input5;

     @ComplexInput(identifier = "input6",
                   abstrakt = "input6 abstract",
                   title = "input6 title",
                   minOccurs = 1,
                   maxOccurs = 1,
                   maximumMegaBytes = 10,
                   binding = TestIData.class)
     public Object input6;

     @LiteralInput(identifier = "input7",
                   title = "input7 title",
                   abstrakt = "input7 abstract",
                   minOccurs = 1,
                   maxOccurs = 1,
                   defaultValue = "1",
                   uom = "m")
     public int input7;

     @LiteralInput(identifier = "input8",
                   title = "input8 title",
                   abstrakt = "input8 abstract",
                   minOccurs = 1,
                   maxOccurs = 10,
                   defaultValue = "1",
                   uom = "m")
     public List<Integer> input8;
     @LiteralOutput(identifier = "output5",
                    abstrakt = "output5 abstract",
                    title = "output5 title",
                    uom = "m")
     public TestEnum output5;
     @ComplexOutput(identifier = "output6",
                    abstrakt = "output6 abstract",
                    title = "output6 title",
                    binding = TestIData.class)
     public Object output6;
     @LiteralOutput(identifier = "output7",
                    title = "output7 title",
                    abstrakt = "output7 abstract",
                    uom = "m")
     public int output7;

     @LiteralInput(identifier = "input1",
                   abstrakt = "input1 abstract",
                   title = "input1 title",
                   minOccurs = 1,
                   maxOccurs = 1,
                   defaultValue = "1", uom = "m")
     public void setInput1(int input) {
     }

     @LiteralInput(identifier = "input2",
                   abstrakt = "input2 abstract",
                   title = "input2 title",
                   minOccurs = 1,
                   maxOccurs = 1,
                   defaultValue = "asdf",
                   uom = "m")
     public void setInput2(String input) {
     }

     @ComplexInput(identifier = "input3",
                   abstrakt = "input3 abstract",
                   title = "input3 title",
                   minOccurs = 1,
                   maxOccurs = 1,
                   maximumMegaBytes = 10,
                   binding = TestIData.class)
     public void setInput3(Object input) {
     }

     @LiteralInput(identifier = "input4",
                   abstrakt = "input4 abstract",
                   title = "input4 title",
                   minOccurs = 1,
                   maxOccurs = 1,
                   defaultValue = "A",
                   uom = "m")
     public void setInput4(TestEnum input) {
     }

     @Execute
     public void execute() {
     }

     @LiteralOutput(identifier = "output1",
                    abstrakt = "output1 abstract",
                    title = "output1 title",
                    uom = "m")
     public int getOutput1() {
         return 0;
     }

     @LiteralOutput(identifier = "output2",
                    abstrakt = "output2 abstract",
                    title = "output2 title",
                    uom = "m")
     public String getOutput2() {
         return null;
     }

     @ComplexOutput(identifier = "output3",
                    abstrakt = "output3 abstract",
                    title = "output3 title",
                    binding = TestIData.class)
     public Object getOutput3() {
         return null;
     }

     @LiteralOutput(identifier = "output4",
                    abstrakt = "output4 abstract",
                    title = "output4 title",
                    uom = "m")
     public TestEnum getOutput4() {
         return null;
     }

     @BoundingBoxOutput(identifier="bboxOutput1")
     public OwsBoundingBox getBBoxOutput1() {
         return null;
     }
 }

}
