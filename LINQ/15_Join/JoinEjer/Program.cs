namespace JoinEjer
{
    internal class Program
    {
        static void Main(string[] args)
        {
            /*
            EJERCICIO 1 Un colegio tiene alumnos y matrículas.
            Se desea obtener un listado de alumnos mayores de edad, con matrículas activas, mostrando: Nombre del alumno, Edad, Curso, Fecha de matrícula
            Además:
            Ordenar por edad descendente
            Obtener el alumno más joven y más viejo
            Calcular la edad promedio
            Verificar si existe algún alumno menor de edad matriculado
            Contar matrículas activas
             */

            var alumnos = new List<Alumno1>
        {
            new Alumno1{ Id = 1, Nombre = "Ana", FechaNacimiento = new DateTime(DateTime.Now.Year - 20, 5, 1) },
            new Alumno1{ Id = 2, Nombre = "Luis", FechaNacimiento = new DateTime(DateTime.Now.Year - 17, 3, 12) },
            new Alumno1{ Id = 3, Nombre = "María", FechaNacimiento = new DateTime(DateTime.Now.Year - 22, 11, 20) },
            new Alumno1{ Id = 4, Nombre = "Carlos", FechaNacimiento = new DateTime(DateTime.Now.Year - 18, 8, 9) }
        };

            var matriculas = new List<Matricula1>
            {
            new Matricula1{ AlumnoId = 1, Curso = "Matemáticas", Activa = true, Fecha = DateTime.Now },
            new Matricula1{ AlumnoId = 2, Curso = "Lengua", Activa = true, Fecha = DateTime.Now },
            new Matricula1{ AlumnoId = 3, Curso = "Historia", Activa = false, Fecha = DateTime.Now },
            new Matricula1{AlumnoId = 4, Curso = "Física", Activa = true, Fecha = DateTime.Now }
        };
            var alumnosMatriculados = alumnos
                .Join(
                    matriculas,
                    a => a.Id,
                    m => m.AlumnoId,
                    (a, m) => new
                    {
                        a.Nombre, 
                        a.Edad,
                        m.Curso,
                        m.Fecha,
                        m.Activa
                    })
                .Where(x => x.Edad >= 18 && x.Activa)
                .OrderByDescending(x => x.Edad)
                .ToList();
            var masJoven = alumnosMatriculados.MinBy(a => a.Edad);
            var masViejo = alumnosMatriculados.MaxBy(a => a.Edad);
            var edadPromedio = alumnosMatriculados.Average(a => a.Edad);
            bool hayMenorDeEdad = alumnos.Any(a => a.Edad < 18 && matriculas.Any(m => m.AlumnoId == a.Id && m.Activa));
            int totalMatriculasActivas = matriculas.Count(m => m.Activa);

       
            Console.WriteLine($"Alumno más joven: {masJoven.Nombre}, Edad: {masJoven.Edad}");
            Console.WriteLine($"Alumno más viejo: {masViejo.Nombre}, Edad: {masViejo.Edad}");
            Console.WriteLine($"Edad promedio: {edadPromedio}");
            Console.WriteLine($"¿Hay menores de edad matriculados? {(hayMenorDeEdad ? "Sí" : "No")}");
            Console.WriteLine($"Total de matrículas activas: {totalMatriculasActivas}");

            /*
             EJERCICIO 2 – Se desea analizar las notas de los alumnos por materia.
            Mostrar:
            Promedio por alumno
            Materias aprobadas (nota ≥ 6)
            Si todos los alumnos aprobaron
            Suma total de notas
            Primera y última nota registrada
             */


            var alumnos2 = new[]
{
    new { Id = 1, Nombre = "Ana" },
    new { Id = 2, Nombre = "Luis" },
    new { Id = 3, Nombre = "María" }
};

            var notas2 = new[]
            {
    new { AlumnoId = 1, Materia = "Matemáticas", Valor = 8 },
    new { AlumnoId = 1, Materia = "Lengua", Valor = 7 },
    new { AlumnoId = 2, Materia = "Matemáticas", Valor = 4 },
    new { AlumnoId = 3, Materia = "Historia", Valor = 9 },
};
            var analisisNotas = alumnos2
                .GroupJoin(
                    notas2,
                    a => a.Id,
                    n => n.AlumnoId,
                    (a, ns) => new
                    {
                        a.Nombre,
                        Promedio = ns.Any() ? ns.Average(n => n.Valor) : 0,
                        MateriasAprobadas = ns.Where(n => n.Valor >= 6).Select(n => n.Materia).ToList(),
                        TodasAprobadas = ns.All(n => n.Valor >= 6),
                        SumaTotalNotas = ns.Sum(n => n.Valor),
                        PrimeraNota = ns.OrderBy(n => n.Valor).FirstOrDefault()?.Valor,
                        UltimaNota = ns.OrderByDescending(n => n.Valor).FirstOrDefault()?.Valor
                    })
                .ToList();
            foreach (var alumno in analisisNotas)
            {
                Console.WriteLine($"Alumno: {alumno.Nombre}");
                Console.WriteLine($"Promedio: {alumno.Promedio}");
                Console.WriteLine($"Materias Aprobadas: {string.Join(", ", alumno.MateriasAprobadas)}");
                Console.WriteLine($"¿Todas Aprobadas? {(alumno.TodasAprobadas ? "Sí" : "No")}");
                Console.WriteLine($"Suma Total de Notas: {alumno.SumaTotalNotas}");
                Console.WriteLine($"Primera Nota: {alumno.PrimeraNota}");
                Console.WriteLine($"Última Nota: {alumno.UltimaNota}");
                Console.WriteLine();
            }
        bool todosAprobaron = analisisNotas.All(a => a.TodasAprobadas);
        Console.WriteLine($"¿Todos los alumnos aprobaron todas las materias? {(todosAprobaron ? "Sí" : "No")}");
        }
        public static int CalcularEdad(DateTime fechaNacimiento)
        {
            var hoy = DateTime.Today;
            var edad = hoy.Year - fechaNacimiento.Year;
            if (fechaNacimiento.Date > hoy.AddYears(-edad)) edad--;
            return edad;
        }
        public class Alumno1
        {
            public int Id { get; set; }
            public string Nombre { get; set; }
            public DateTime FechaNacimiento { get; set; }
            // En la clase Alumno1, la propiedad Edad debe llamar al método estático
            public int Edad => Program.CalcularEdad(FechaNacimiento);
        }

        public class Matricula1
        {
            public int AlumnoId { get; set; }
            public string Curso { get; set; }
            public bool Activa { get; set; }
            public DateTime Fecha { get; set; }
        }


    }
}
