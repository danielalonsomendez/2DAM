namespace ConObjetos
{
    internal class Program
    {
        static void Main(string[] args)
        {
            var people = new List<Person>
            {//Imagine that the people object is not a tiny list of two people, but a huge worldwide database consisting
               //of hundreds of millions of records.
                new Person("John","USA"),
                new Person("bETTY","UK"),
            };
            var AllAmericans = people.Where(item => item.Country == "USA");
            var notAllAmericans = AllAmericans.Take(100);
            var numbers = new[] { 4, 2, 7, 10, 12, 5,4,2 };
            // METHOD SYNTAX
            var aa = numbers.Where(item => item < 10).OrderBy(item => item);
            Console.WriteLine(string.Join(",", aa));
            // QUERY SYNTAX
            var bb = (from item in numbers
                     where item < 10
                     orderby item
                     select item).Distinct();
        Console.WriteLine(string.Join(",", bb));


        }
    }
    public class Person
    {
        public string Name { get; set; }
        public string Country { get; set; }
        public Person(string Name, string Country)
        {
            this.Name = Name;
            this.Country = Country;
        }
    }

}
