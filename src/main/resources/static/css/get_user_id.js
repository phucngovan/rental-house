function getId() {
    $(document).ready(function () {
        //get data rental_house_id
        var rental_id = [[${house.id}]];
        $("#rental_id").val(rental_id);
        $("#user_id").val([[${myName}]]);
    });
}
