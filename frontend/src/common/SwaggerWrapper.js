import React from 'react';
import SwaggerUI from "swagger-ui-react";
import 'swagger-ui-react/swagger-ui.css'

const SwaggerWrapper = () => {
    return (
        <SwaggerUI url='http://localhost:8080/v2/api-docs' />
    );
};

export default SwaggerWrapper;