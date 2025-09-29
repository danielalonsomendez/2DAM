using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _08MinMax
{
    public class Person
    {
        public int Id { get; }
        public string Name { get; }
        public IEnumerable<Pet> Pets;

        public Person(int id, string name, IEnumerable<Pet> pets)
        {
            Id = id;
            Name = name;
            Pets = pets;
        }
    }
}
