"use strict";

import {byId, verberg, toon, setText} from "./util.js";

const brouwer = JSON.parse(sessionStorage.getItem("brouwer"));
const brouwerid = brouwer.id;
getBieren(brouwerid);


async function getBieren(brouwerId){
    const response =  await fetch(`bieren/vanBrouwer/${brouwerId}`);
    if (response.ok){
        const bieren = await response.json();
        bieren.forEach(bier => {
            var bieren = byId("bieren");
            const bierItem = document.createElement("p");
            bieren.appendChild(bierItem);
            bierItem.innerText = bier.naam;
            bierItem.onclick = function () {
                sessionStorage.setItem("bier", JSON.stringify(bier));
                window.location =
            }
            }

        )
    } else toon("storing");
}