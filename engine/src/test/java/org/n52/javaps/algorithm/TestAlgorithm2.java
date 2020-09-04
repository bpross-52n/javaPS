package org.n52.javaps.algorithm;

import org.n52.javaps.description.TypedProcessDescription;
import org.n52.javaps.description.impl.TypedProcessDescriptionFactory;
import org.n52.javaps.engine.ProcessExecutionContext;

public class TestAlgorithm2 extends AbstractAlgorithm {

    @Override
    public void execute(ProcessExecutionContext context) throws ExecutionException {
        // TODO Auto-generated method stub

    }

    @Override
    protected TypedProcessDescription createDescription() {
        // TODO Auto-generated method stub
        return new TypedProcessDescriptionFactory().process().withIdentifier("org.n52.javaps.algorithm.TestAlgorithm2").withVersion("1.0.0").build();
    }

}
