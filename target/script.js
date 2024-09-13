document.addEventListener('DOMContentLoaded', function () {
    // Sample bed data
    const bedsData = [
        { floor: 1, beds: [{ id: 101, available: true }, { id: 102, available: false }, { id: 103, available: true }] },
        { floor: 2, beds: [{ id: 201, available: true }, { id: 202, available: false }, { id: 203, available: true }] }
    ];

    // Display initial beds
    const bedListContainer = document.getElementById('bed-list');
    displayBeds(bedsData);

    // Handle form submission
    const form = document.getElementById('bed-allocation-form');
    form.addEventListener('submit', function (e) {
        e.preventDefault();
        const patientName = document.getElementById('patientName').value;
        const patientAge = document.getElementById('patientAge').value;
        const bedId = parseInt(document.getElementById('bedId').value);

        // Find the bed and mark it as occupied
        allocateBed(bedId, bedsData);

        // Refresh bed list
        bedListContainer.innerHTML = '';
        displayBeds(bedsData);

        alert(`Bed ${bedId} allocated to ${patientName} (Age: ${patientAge})`);
    });

    // Display beds on the UI
    function displayBeds(bedsData) {
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
    }

    // Function to allocate a bed
    function allocateBed(bedId, bedsData) {
        bedsData.forEach(floorData => {
            floorData.beds.forEach(bed => {
                if (bed.id === bedId && bed.available) {
                    bed.available = false;
                }
            });
        });
    }
});
