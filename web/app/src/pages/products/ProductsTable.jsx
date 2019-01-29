import React, {Fragment} from 'react';
import TableRow from './products-table/TableRow';
import {splitOnRows} from '../../utils';

export default ({products}) => (
    <Fragment>
        {splitOnRows(products, 3)
            .map((row, index) => <TableRow key={index} products={row} />)
        }
    </Fragment>
);