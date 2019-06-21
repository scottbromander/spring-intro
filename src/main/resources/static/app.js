$(document).ready(function() {

    $("#btnSearchId").on('click', function(event) {
        const id = $("#searchId").val();

        $.ajax({
            type: "GET",
            url: "/search/" + id,
            success: function(response) {
                console.log("Found employee: ", response);
            }
        })
    });

    $("#employeeForm").on('submit', function(event) {
        event.preventDefault();

        const data = {
            firstName : $("#firstName").val(),
            lastName : $("#lastName").val(),
            id : $("#id").val(),
            salary : $("#salary").val(),
            position : $("#position").val()
        }

        $.ajax({
            type: "POST",
            url: "/add/employee",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            beforeSend: function() {
                console.log('hi')
            },
            success: function(response) {
                console.log(response);
                getEmployees();
            }
        })
    });
});

function getEmployees() {
    $.ajax({
        type: "GET",
        url: "/get/employees",
        success: function(response) {
            console.table(response);
        }
    })
}



