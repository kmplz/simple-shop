import React from 'react';
import BrandItem from './brand-section/BrandItem';
import Section from '../../components/Section';

export default ({items}) => (
  <Section title="Brand">
    {items.map(item => <BrandItem key={item.id} name={item.name} count={item.count} />)}
  </Section>
);