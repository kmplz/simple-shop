import React from 'react';
import Button from '../../../components/Button';

export default ({product: {name, price, rating}}) => (
  <div className="col-sm">
    <img alt="product-pic" src="https://via.placeholder.com/250x250.jpg" style={{"width": "100%"}} />
    <h5 className="font-weight-bold text-center my-3">{name}</h5>
    <div className="my-3 text-center text-muted rating">
      {[1, 2, 3, 4, 5].map(star => <span
        className={`fa fa-star star ${star <= rating && 'active'}`}
        key={star}
      />)}
    </div>
    <h6 className="text-center text-muted">${price}</h6>
    <Button>Add to cart</Button>
  </div>
);