"use strict";

import {byId, verberg, toon, setText} from "./util.js";

const brouwer = JSON.parse(sessionStorage.getItem("brouwer"));
if (brouwer !== null){
    setText("brouwer", brouwer.naam);
    const brouwerid = brouwer.id;
    getBieren(brouwerid);
}



async function getBieren(brouwerId){
    const response =  await fetch(`bieren/vanBrouwer/${brouwerId}`);
    if (response.ok){
        const bieren = await response.json();
        let index = 0;
        bieren.forEach(bier => {
            index ++;
            var bieren = byId("bieren");
            const bierItem = document.createElement("p");
            bieren.appendChild(bierItem);
            bierItem.innerText = bier.naam;
            if ((index % 2 ) === 0){
                bierItem.classList.add('even');
            } else {
                bierItem.classList.add('odd');
            }
            bierItem.onclick = function () {
                sessionStorage.setItem("bier", JSON.stringify(bier));
                window.location = "bier.html";
            }
            }

        )
    } else toon("storing");
}