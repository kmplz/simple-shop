import React from 'react';

export default ({name, count}) => (
    <div className="d-flex justify-content-between py-2 px-2 brand">
        <span className="text-muted">{name}</span>
        <span>{count}</span>
    </div>
);