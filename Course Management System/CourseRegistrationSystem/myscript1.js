function showEnrolledStudents(){
    fetch("http://localhost:8080/courses/enrolled")
    .then((response) => response.json())
    .then((courses) => {
        const dataTable = document.getElementById("enrolledtable");
        courses.forEach(course => {
            var row = 
            `<tr>
                <td>${course.name}</td>
                <td>${course.emailId}</td>
                <td>${course.courseName}</td>
            </tr>`
            dataTable.innerHTML+=row;
        })
    })
}