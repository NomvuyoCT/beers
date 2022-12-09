"use strict";

import {byId, setText, toon} from "./util.js";

const id = sessionStorage.getItem("bestelId");
setText("id", id);