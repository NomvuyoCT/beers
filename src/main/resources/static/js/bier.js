"use strict";

import {byId, toon, verberg, setText} from "./util.js";

verberg("storing");
const bier = JSON.parse(sessionStorage.getItem("bier"));
if (bier !== null){
    toon("bierInfo");
    const naam = byId("bierNaam");
    naam.innerText = bier.naam;
    const alcohol = byId("alcohol");
    alcohol.innerText = bier.alcohol;
    const prijs = byId("prijs");
    prijs.innerText = bier.prijs;
    let mandjeBieren = JSON.parse(sessionStorage.getItem("mandjeBieren"));
    if (mandjeBieren === null){
        mandjeBieren = [];
    }
    for (const mandjeBier of mandjeBieren){
        if (bier.id === mandjeBier.id){
            setText("mandje", "Toevoegen");
        }
    }
    const mandje = byId("mandje");
    mandje.onclick = function (){
        if (mandje.innerText === "In Mandje"){
            mandjeBieren.push(bier);
            sessionStorage.setItem("mandjeBieren", JSON.stringify(mandjeBieren));
            setText("mandje", "Toevoegen");
        } else {
            window.location = "bestel.html";
        }
    }
} else {
    toon("storing");
}



