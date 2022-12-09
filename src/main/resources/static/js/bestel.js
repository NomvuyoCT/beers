"use strict";

import {byId, verberg, setText, toon} from "./util.js";

const mandjeBieren = JSON.parse(sessionStorage.getItem("mandjeBieren"));
getBieren();
byId("bestellen").onclick = async function (){
    verbergAlles();
    const naamInput = byId("naam");
    if (! naamInput.checkValidity()){
        toon("naamFout");
        naamInput.focus();
        return;
    }
    const straatInput = byId("straat");
    if (! straatInput.checkValidity()){
        toon("straatFout");
        straatInput.focus();
        return;
    }
    const huisnummerInput = byId("huisnummer");
    if (! huisnummerInput.checkValidity()){
        toon("huisnummerFout");
        huisnummerInput.focus();
        return;
    }
    const postcodeInput = byId("postcode");
    if (! postcodeInput.checkValidity()){
        toon("postcodeFout");
        postcodeInput.focus();
        return;
    }
    const gemeenteInput = byId("gemeente");
    if (! gemeenteInput.checkValidity()){
        toon("gemeenteFout");
        gemeenteInput.focus();
        return;
    }
    const bestelling = {
        bestelling: {
            naam: naamInput.value,
            straat: straatInput.value,
            huisnummer: huisnummerInput.value,
            postcode: postcodeInput.value,
            gemeente: gemeenteInput.value
        },
        bieren: mandjeBieren
    }
    bestellen(bestelling);

}
async function bestellen(bestelling){
    const response = await fetch("bestellingen",
        {method: "POST",
            headers: {'Content-Type': "application/json"},
            body: JSON.stringify(bestelling)
        });
    if (response.ok) {
        const id = await response.json();
        sessionStorage.removeItem("mandjeBieren");
        sessionStorage.setItem("bestelId", id);
        window.location = "bestellingGelukt.html";
    } else {
        toon("storing");
    }
}



function getBieren(){
    toon("mandje");
    const bierenBody = byId("bierenBody");
    let totaalPrijs = 0;
    for (const mandjeBier of mandjeBieren){
        const tr = bierenBody.insertRow();
        tr.insertCell().innerText = mandjeBier.naam;
        tr.insertCell().innerText = mandjeBier.prijs;
        totaalPrijs += mandjeBier.prijs;
    }
    const tr = bierenBody.insertRow();
    const totaalVakje = tr.insertCell();
    totaalVakje.innerText = "Totaal:";
    totaalVakje.classList.add('toTheRight');
    tr.insertCell().innerText = totaalPrijs;
}
function verbergAlles(){
    verberg("naamFout");
    verberg("straatFout");
    verberg("huisnummerFout");
    verberg("postcodeFout");
    verberg("gemeenteFout");
    verberg("storing");
}
