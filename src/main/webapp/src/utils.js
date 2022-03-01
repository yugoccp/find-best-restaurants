export const formatMoney = (val) => `$` + val
export const parseMoney = (val) => val.replace(/^\$/, '')