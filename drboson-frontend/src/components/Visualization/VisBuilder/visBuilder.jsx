import React, { useState } from 'react';
import { visTypeConfigs, visTypeConfigurers } from '../VisTypeSelector/VisTypes';
import VisTypeSelector from '../VisTypeSelector/visTypeSelector';
import VisConfigurer from '../VisConfigurer/visConfigurer';

const defaultConfigurer = {
    isOpen: false,
    configurer: VisConfigurer,
}

const VisBuilder = (props) => {
    const [isSelectorOpen, setIsSelectorOpen] = useState(true);
    const [configurer, setConfigurer] = useState(defaultConfigurer);

    const closeBuilder = () => {
        setIsSelectorOpen(false);
        setConfigurer(defaultConfigurer);
    }

    const selectVisualizationType = (visType) => {
        const newConfigurer = {
            isOpen: true,
            configurer: visTypeConfigurers[visType],
        }
        setIsSelectorOpen(false);
        setConfigurer(newConfigurer);
    }

    const Configurer = configurer.configurer;

    return (
        <React.Fragment>
            <VisTypeSelector
                isOpen={isSelectorOpen}
                visTypeConfigs={visTypeConfigs}
                onTypeSelection={selectVisualizationType}
                onRequestClose={closeBuilder}
            />

            <Configurer
                isOpen={configurer.isOpen}
                onRequestClose={closeBuilder}
            />
        </React.Fragment>
    );
}

export default VisBuilder;