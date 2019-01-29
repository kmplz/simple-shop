import React from 'react';
import Section from '../../components/Section';
import CartItem from './cart-section/CartItem';
import Button from '../../components/Button';

export default ({items, subtotal}) => (
  <Section title="Cart">
    {items.map(item => <CartItem key={item.id} item={item} />)}
    <h6 className="text-center font-weight-bolder py-3 border-bottom">Subtotal: ${subtotal}</h6>
    <Button>View cart</Button>
  </Section>
);