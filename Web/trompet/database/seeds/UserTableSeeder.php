<?php

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Str;
class UserTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('users')->insert([
            'name' => 'Alwan',
            'email' => 'alwan@mail.com',
            'password' => bcrypt('password'),
            'noHP' => 909090,
            'address' =>"bantu nggal",
            'api_token' => str::random(100),            
        ]);
    }
}
