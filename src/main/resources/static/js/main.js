function send(id){
    var auctionLogDTO = {};
    auctionLogDTO["newPrice"] = $("#newPrice").val();

    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/auctions/" + id,
        data : JSON.stringify(auctionLogDTO),
        headers: {
            "X-CSRF-TOKEN": $("#csrf-token").val()
        },
        dataType : 'json',
        success : function(data) {
            new PNotify.success({
                title: 'Sukces!',
                text: 'Udało się podbić cenę aukcji!'
            });
        },
        error : function (data){
            PNotify.error({
               title: 'Błąd!',
               text: 'Nie udało się podbić ceny aukcji, spróbuj ponownie!'
            });
        }
    });
}