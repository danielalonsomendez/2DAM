namespace ContainsEjer
{
    internal class Program
    {
        static void Main(string[] args)
        {
            // EJ 1: ¿El array contiene el número 10?
            int[] numeros = { 5, 10, 15, 20 };
            Console.WriteLine(numeros.Contains(10));
            // EJ 2: ¿La lista de títulos contiene "LINQ"?
            List<string> titulos = new List<string> { "C#", "LINQ", "ASP.NET" };
            Console.WriteLine(titulos.Contains("LINQ"));
            // EJ 3: ¿Hay alguna persona llamada "Luis"?
        List<Persona> personas = new List<Persona>
        {
            new Persona { Nombre = "Ana", Edad = 25 },
            new Persona { Nombre = "Luis", Edad = 30 }
        };
            Console.WriteLine(personas.Any(p => p.Nombre == "Luis"));
            // EJ 4: ¿La lista de productos contiene "Ratón"?
            List<string> productos = new List<string> { "Teclado", "Monitor", "Ratón" };
            Console.WriteLine(productos.Contains("Ratón"));
            // EJ 5: ¿El array de caracteres contiene la letra 'A'?
            char[] letras = { 'B', 'C', 'A', 'D' };
            Console.WriteLine(letras.Contains('A'));
            // EJ 6: ¿Algún estudiante se llama "Lucía"?

        List<Estudiante> estudiantes = new List<Estudiante>
{
     new Estudiante { Nombre = "Carlos", Nota = 7.5 },
    new Estudiante { Nombre = "Lucía", Nota = 9.0 }
};
            Console.WriteLine(estudiantes.Any(e => e.Nombre == "Lucía"));
            // EJ 7: ¿La lista de IDs contiene el número 3?
            List<int> ids = new List<int> { 1, 2, 3, 4 };
            Console.WriteLine(ids.Contains(3));

            // EJ 8: ¿Alguna palabra contiene la subcadena "net" ?
            string[] palabras = { "internet", "red", "conexión", "netflix" };
            Console.WriteLine(palabras.Any(p => p.Contains("net")));
            // EJ 9: ¿La lista de números contiene el valor 100?
            List<int> valores = new List<int> { 50, 75, 100, 125 };
            Console.WriteLine(valores.Contains(100));
            // EJ 10: ¿Algún empleado se llama "Jorge"?

        List<Empleado> empleados = new List<Empleado>
{
    new Empleado { Nombre = "Marta", Salario = 1200 },
    new Empleado { Nombre = "Jorge", Salario = 1500 }
};
            Console.WriteLine(empleados.Any(emp => emp.Nombre == "Jorge"));



        }
    class Persona { public string Nombre; public int Edad; }
    class Estudiante { public string Nombre; public double Nota; }
    class Empleado { public string Nombre; public double Salario; }

    }
}
