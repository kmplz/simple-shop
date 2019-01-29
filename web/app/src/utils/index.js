export const splitOnRows = (items, itemsPerRow) => {
    const rows = [];
    items.forEach((item, index) => {
        const rowNum = Math.floor(index / itemsPerRow);
        if (!rows[rowNum]) {
            rows[rowNum] = [];
        }
        rows[rowNum].push(item);
    });
    return rows;
};