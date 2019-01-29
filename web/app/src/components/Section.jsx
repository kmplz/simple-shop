import React, {Fragment} from 'react';

export default ({title, children}) => (
    <Fragment>
        <h5 className="mt-5 pb-2 section-header">{title}</h5>
        {children}
    </Fragment>
);