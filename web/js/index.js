function delFruit(fId) {
    if (confirm('确认删除?')){
        /*当前地址栏*/
        window.location.href='del.do?fid='+fId;
    }
}

function page(pageNo) {
    window.location.href='index_fruit?pageNo='+pageNo;
    console.log(pageNo);
}