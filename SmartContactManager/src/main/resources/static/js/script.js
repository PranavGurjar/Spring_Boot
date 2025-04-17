/*alert("hello pranav");*/
console.log("I am Pranav Mahajan");

// function toggleSidebar(){

// }

const toggleSidebar=()=>{
    if($(".sidebar").is(":visible")){
        //true mean band karana
        $(".sidebar").css("display","none");
        $(".content").css("margin-left","0%");
    }else{
        //false mean show karana
        $(".sidebar").css("display","block");
        $(".content").css("margin-left","20%");
    }
};

const search=()=>{
    // console.log("searching...");
    let query = $("#search-input").val();
    console.log(query);

    if(query == ""){
        $(".search-result").hide();
    }else{
        //search
        //console.log(query);

        //sending request to server
        let url = `http://localhost:8080/search/${query}`;
        fetch(url).then((response)=>{
            return response.json();
        })
        .then((data)=>{
            //data...
            //console.log(data);
            let text = `<div class='list-group'>`

            data.forEach((contact)=>{
                text+=`<a href='/user/${contact.cId}/contact' class='list-group-item list-group-item-action'>${contact.name}</a>`
            });

            text+='</div>'

            $(".search-result").html(text);
            $(".search-result").show();
        });

    }
};


//first request to server to create order
const paymentStart=()=>{
    // alert("payment started...");
    let amount = $("#payment_field").val();
	console.log(amount);
    //alert(amount);

    if(amount=="" || amount==null){
        //alert("Amount is required!!");
		swal("Failed!", "Amount is required!!", "error");
        return;
    }
    
    //code
    //we will use ajax to send request to server to create order - jquery ajax
    $.ajax({
        url : '/user/createOrder',
        data : JSON.stringify({amount:amount, info:'orderRequest'}),
        contentType : 'application/json',
        type : "POST",
        dataType : 'json',
        success : function(response){
            //invoked where success
            console.log(response);
            if(response.status == "created"){
                //open payment form
                let options={
                    key : "rzp_test_xGyx3FPMpN9fux",
                    amount : response.amount,
                    currency : "INR",
                    name : "Smart Contact Manager",
                    description : "Donation",
                    image : "https://www.google.com/imgres?q=profile%20image&imgurl=https%3A%2F%2Fimg.freepik.com%2Ffree-vector%2Fblue-circle-with-white-user_78370-4707.jpg%3Fsemt%3Dais_hybrid%26w%3D740&imgrefurl=https%3A%2F%2Fwww.freepik.com%2Ffree-photos-vectors%2Fprofile&docid=WIYPytbMl_8XfM&tbnid=IXrxgjgj6FwBhM&vet=12ahUKEwiowYrXhd-MAxXHe2wGHQZFPbsQM3oFCIYBEAA..i&w=740&h=740&hcb=2&ved=2ahUKEwiowYrXhd-MAxXHe2wGHQZFPbsQM3oFCIYBEAA",
                    order_id : response.id,

                    handler:function(response){
                        console.log(response.razorpay_payment_id);
                        console.log(response.razorpay_order_id);
                        console.log(response.razorpay_signature);
                        console.log("Payment successful !!");
                        //alert("Congrats! Payment successful !!");
						swal("Good job!", "Congrats! Payment successful !!", "success");
                    },
                   "prefill": {
                        "name": "", 
                        "email": "",
                        "contact": "" 
                    },
                    "notes": {
                        "address": "Pranav WebSite"
                    },
                    "theme": {
                        "color": "#3399cc"
                    }
                };

                let rzp = new Razorpay(options);

                rzp.on('payment.failed', function (response){
                    console.log(response.error.code);
                    console.log(response.error.description);
                    console.log(response.error.source);
                    console.log(response.error.step);
                    console.log(response.error.reason);
                    console.log(response.error.metadata.order_id);
                    console.log(response.error.metadata.payment_id);
                    //alert("Oops payment failed!!");
					swal("Failed!", "Oops payment failed!!", "error");
                   });
                
                rzp.open();

            }
        },
        error:function(error){
            //invoked when error
            console.log(error);
            //alert("something wents wrong!!");
			swal("Failed!", "something wents wrong!!", "error");
        }
    }

)};