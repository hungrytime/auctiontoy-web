function pageMove(num) {
    alert(num)
    alert(num)
    try {
        $("#page").val(num);
        $("#searchForm").submit();
    } catch(e) {
        alert(e.message);
    }
}