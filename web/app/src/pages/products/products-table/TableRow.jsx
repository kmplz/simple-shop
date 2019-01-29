import React from 'react';
import ProductCard from './ProductCard';

export default ({products}) => (
    <div className="row mb-4">
        {products.map(product => <ProductCard key={product.id} product={product} />)}
    </div>
);