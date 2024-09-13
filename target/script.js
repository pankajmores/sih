document.addEventListener('DOMContentLoaded', function() {
    // Example data: You could fetch this from your back-end API
    const bedsData = [
        { floor: 1, beds: [{ id: 101, available: true }, { id: 102, available: false }, { id: 103, available: true }] },
        { floor: 2, beds: [{ id: 201, available: false }, { id: 202, available: true }, { id: 203, available: true }] },
        { floor: 3, beds: [{ id: 301, available: false }, { id: 302, available: true }, { id: 303, available: false }] }
    ];

    const bedListContainer = document.getElementById('bed-list');

    bedsData.forEach(floorData => {
        const floorSection = document.createElement('div');
        floorSection.classList.add('floor-section');
        floorSection.innerHTML = `<h2>Floor ${floorData.floor}</h2>`;

        floorData.beds.forEach(bed => {
            const bedElement = document.createElement('div');
            bedElement.classList.add('bed', bed.available ? 'available' : 'occupied');
            bedElement.innerText = bed.id;
            floorSection.appendChild(bedElement);
        });

        bedListContainer.appendChild(floorSection);
    });
});
