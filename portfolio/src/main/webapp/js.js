var tags = document.getElementsByClassName("row")
console.log(tags)

for (let i = 0; i < tags.length; i++) {

    tags[i].addEventListener("click", function () {
        console.log(this)
        for (let i = 1; i < this.children.length; i++) {{
                if (this.children[i].style.display == "block") {
                    this.children[i].style.display = "none"
                }
                else {
                    this.children[i].style.display = "block"
                }   
            }
        }

    })
}

function sleep(){
    var start = new Date().getTime();
    while (new Date().getTime() < start + 1000);
}



function getMessages(){
    var messages = []
    fetch ('/messages')
    .then((response)=>response.json())
    .then((result)=>{
        console.log(result)
        messages=result
    })
    .then(()=>{
        if(document.getElementsByClassName("oneMessage").length==0){
            for(let i=0; i<messages.length; i++){
                console.log(messages[i])
                var root=document.getElementById("listMessagesWrapper")
                var oneMessageDiv=document.createElement("div")
                oneMessageDiv.id=messages[i].id
                oneMessageDiv.className="oneMessage"
                var nameHeader = document.createElement("h3")
                nameHeader.innerHTML=messages[i].name+":"
                var message=document.createElement("p")
                message.innerHTML="    "+messages[i].message
                var deleteBtn=document.createElement("button")
                deleteBtn.innerHTML="🗑️"
                deleteBtn.onclick=function(){
                    deleteMessage(messages[i].id)
                }
                oneMessageDiv.appendChild(nameHeader)
                oneMessageDiv.appendChild(message)
                oneMessageDiv.appendChild(deleteBtn)
                oneMessageDiv.style.display="flex"
                oneMessageDiv.style.flexDirection="row"

                root.appendChild(oneMessageDiv)
                
            }
        }
    })

}

function deleteMessage(id){
    const params = new URLSearchParams();
    params.append('id', id);
    fetch('/delete_message', {method: 'POST', body: params})
    .then(()=>{window.location.href="http://cwang-sps-summer22.wl.r.appspot.com/#scrollspyHeading4";settimeout(()=>{window.location.reload()},0)})
}
