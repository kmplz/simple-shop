import React from 'react';

export default ({item: {name, price, count}}) => (
    <div className="d-flex border-bottom py-2 justify-content-between align-self-center">
        <div className="d-flex flex-column">
            <span className="text-muted">{name}</span>
            <strong className="text-muted">{count} x ${price}</strong>
        </div>
        <img src="https://via.placeholder.com/50x50.jpg" />
    </div>
);