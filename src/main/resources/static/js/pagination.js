function pageMove(num) {
    try {
        $("#page").val(num);
        $("#searchForm").submit();
    } catch(e) {
        alert(e.message);
    }
}