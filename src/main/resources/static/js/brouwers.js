"use strict";
import {byId, toon, verberg, setText} from "./util.js";

verberg("storing");
const response = await fetch("brouwers");
if (response.ok){
    const brouwers = await response.json();
    let index = 0;
    brouwers.forEach(brouwer =>{
        index ++;
        var lijstBrouwers = byId("lijstBrouwers");
        const brouwerItem = document.createElement("p");
        lijstBrouwers.appendChild(brouwerItem);
        brouwerItem.innerText = `${brouwer.naam}(${brouwer.gemeente})`;
        if ((index % 2) === 0){
            brouwerItem.classList.add('even');
        } else {
            brouwerItem.classList.add('odd');
        }
        brouwerItem.onclick = function (){
            sessionStorage.setItem("brouwer", JSON.stringify(brouwer));
            window.location = "bierenVanBrouwer.html";
        }
        }
    )
} else {
    toon("storing");
}
