namespace AverageEjer
{
    internal class Program
    {
        class Persona
        {
            public string Nombre { get; set; }
            public int Edad { get; set; }
        }
        class Estudiante
        {
            public string Nombre { get; set; }
            public double Nota { get; set; }
        }
        class Proyecto
        {
            public string Nombre { get; set; }
            public int DuracionDias { get; set; }
            public bool Finalizado { get; set; }
        }

        static void Main(string[] args)
        {
            // Ejercicio 1: Define una clase Persona con propiedades Nombre y Edad. Dada una lista de personas, calcula el promedio de edad, la edad mínima y máxima. Verifica si todas las personas son mayores de 18 años.
            List<Persona> personas = new List<Persona>{
                new Persona { Nombre = "Ana", Edad = 20 },
                new Persona { Nombre = "Luis", Edad = 17 },
                new Persona { Nombre = "Marta", Edad = 30 }
            };
            double promedioEdad = personas.Average(p => p.Edad);
            int edadMinima = personas.Min(p => p.Edad);
            int edadMaxima = personas.Max(p => p.Edad);
            bool todasMayoresDe18 = personas.All(p => p.Edad > 18);
            Console.WriteLine(promedioEdad);
            Console.WriteLine(edadMinima);
            Console.WriteLine(edadMaxima);
            Console.WriteLine(todasMayoresDe18);

            // Ejercicio 2: Dado un array de calificaciones (double), calcula el promedio, el valor mínimo y máximo. Cuenta cuántas calificaciones son mayores o iguales a 7.
            double[] calificaciones = { 5.5, 7.8, 9.0, 6.7, 8.2 };
            double promedioCalificaciones = calificaciones.Average();
            double calificacionMinima = calificaciones.Min();
            double calificacionMaxima = calificaciones.Max();
            int countMayorIgual7 = calificaciones.Count(c => c >= 7);
            Console.WriteLine(promedioCalificaciones);
            Console.WriteLine(calificacionMinima);
            Console.WriteLine(calificacionMaxima);
            Console.WriteLine(countMayorIgual7);

            // Ejercicio 3: Dada una lista de palabras, calcula la longitud promedio, la longitud mínima y máxima. Verifica si alguna palabra contiene la letra 'z'.
            List<string> palabras = new List<string> { "perro", "gato", "elefante", "zorro" };
            double longitudPromedio = palabras.Average(p => p.Length);
            int longitudMinima = palabras.Min(p => p.Length);
            int longitudMaxima = palabras.Max(p => p.Length);
            bool algunaContieneZ = palabras.Any(p => p.Contains('z'));
            Console.WriteLine(longitudPromedio);
            Console.WriteLine(longitudMinima);
            Console.WriteLine(longitudMaxima);
            Console.WriteLine(algunaContieneZ);
            // Ejercicio 4: Dado un array de enteros, calcula el promedio, mínimo y máximo de solo los números pares.
            int[] numeros4 = { 1, 2, 3, 4, 5, 6, 7, 8 };
            var numerosPares = numeros4.Where(n => n % 2 == 0);
            double promedioPares = numerosPares.Average();
            int minPares = numerosPares.Min();
            int maxPares = numerosPares.Max();
            Console.WriteLine(promedioPares);
            Console.WriteLine(minPares);
            Console.WriteLine(maxPares);
            // Ejercicio 5: Define una clase Estudiante con propiedades Nombre y Nota. Calcula el promedio, mínimo y máximo solo de los estudiantes aprobados (nota >= 6).
            List<Estudiante> estudiantes = new List<Estudiante>
{
    new Estudiante { Nombre = "Juan", Nota = 7.5 },
    new Estudiante { Nombre = "Ana", Nota = 5.0 },
    new Estudiante { Nombre = "Luis", Nota = 8.0 },
    new Estudiante { Nombre = "Marta", Nota = 6.0 }
};
            var estudiantesAprobados = estudiantes.Where(e => e.Nota >= 6);
            double promedioAprobados = estudiantesAprobados.Average(e => e.Nota);
            double minAprobados = estudiantesAprobados.Min(e => e.Nota);
            double maxAprobados = estudiantesAprobados.Max(e => e.Nota);
            Console.WriteLine(promedioAprobados);
            Console.WriteLine(minAprobados);
            Console.WriteLine(maxAprobados);

            // Ejercicio 6: Dada una lista de proyectos con duración en días y estado, calcula el promedio de duración de los que están finalizados.

        List<Proyecto> proyectos = new List<Proyecto>
{
    new Proyecto { Nombre = "Alpha", DuracionDias = 120, Finalizado = true },
    new Proyecto { Nombre = "Beta", DuracionDias = 90, Finalizado = false },
    new Proyecto { Nombre = "Gamma", DuracionDias = 150, Finalizado = true }
};
            var proyectosFinalizados = proyectos.Where(p => p.Finalizado);
        double promedioDuracionFinalizados = proyectosFinalizados.Average(p => p.DuracionDias);
        Console.WriteLine(promedioDuracionFinalizados);
            // Ejercicio 7: Dado un array de ingresos mensuales, calcula el promedio y detecta si hay meses con ingresos por debajo del 50% del promedio.
            double[] ingresos = { 2500, 1800, 3000, 1200, 2700 };
            double promedioIngresos = ingresos.Average();
            bool algunMesBajo50 = ingresos.Any(i => i < 0.5 * promedioIngresos);
            Console.WriteLine(promedioIngresos);
            Console.WriteLine(algunMesBajo50);
        }
    }
}
