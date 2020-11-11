function send(){
    const id = $("#auction-id").val()

    var auctionLogDTO = {
        newPrice: $("#newPrice").val(),
        auctionId: id
    };

    $.ajax({
        type : "PUT",
        contentType : "application/json",
        url : "/auctions/",
        data : JSON.stringify(auctionLogDTO),
        headers: {
            "X-CSRF-TOKEN": $("#csrf-token").val()
        },
        dataType : 'json',
        success : PNotify.success({
            title: 'Sukces!',
            text: 'Udało się podbić cenę aukcji!'
        }),
        error : PNotify.error({
               title: 'Błąd!',
               text: 'Nie udało się podbić ceny aukcji, spróbuj ponownie!'
        }),
    });
}
