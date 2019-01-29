import React from 'react';
import Filters from './products/Filters';
import Header from '../components/Header';
import Breadcrumbs from '../components/Breadcrumbs';
import Pagination from '../components/Pagination';
import CartSection from './products/CartSection';
import BrandSection from './products/BrandSection';
import ProductsTable from './products/ProductsTable';
import {BRANDS, CART_ITEMS, PRODUCTS} from '../utils/const';

export default () => (
    <main className="container">
        <Breadcrumbs />
        <div className="row">
            <section className="col col-sm-9">
                <Header>Laptops</Header>
                <Filters />
                <ProductsTable products={PRODUCTS} />
                <Pagination />
            </section>
            <section className="col col-sm-3">
                <CartSection items={CART_ITEMS} subtotal="140.00" />
                <BrandSection items={BRANDS} />
            </section>
        </div>
    </main>
);