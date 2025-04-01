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


