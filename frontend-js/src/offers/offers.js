import html from "./offers.html"
import "./offers.css"

const template = document.createElement("template");
template.innerHTML = html;

export default class Offers extends HTMLElement {
    constructor() {
        super();
        const shadow = this.attachShadow({mode: 'open'});
        shadow.append(template.content.cloneNode(true));
    }
}

customElements.define("my-offers", Offers);