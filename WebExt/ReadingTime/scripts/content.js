const article = document.querySelector('article');

if (article) {
    const text = article.textContent;
    const wordMatchRegex = /[^\s]+/g;
    const words = text.match(wordMatchRegex);
    const wordCount = [...words].length;
    const readingTime = Math.ceil(wordCount / 200);
    const badge = document.createElement('p');
    badge.classList.add('color-secondary-badge', "type--caption");
    badge.textContent = `Reading time: ${readingTime} min`;

    const header = document.querySelector('h1');
    const date = document.querySelector('time')?.parentNode;

    (date ?? header).insertAdjacentElement("afterend", badge);
}