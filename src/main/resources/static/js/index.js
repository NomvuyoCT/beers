"use strict";

import {byId, toon, verberg, setText} from "./util.js";
verbergAlles();
getAantalBieren();

function verbergAlles(){
    verberg("aantalBieren");
    verberg("storing");
}

async function getAantalBieren(){
    const response = await fetch("bieren");
    if(response.ok){
        const aantalBieren = await response.json();
        toon("aantalBieren");
        setText("aantalBieren", aantalBieren);
    } else {
        toon("storing");
    }
}