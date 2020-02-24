<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Foods extends Model
{
    protected $table = 'foods';
    protected $fillable =['name', 'image', 'stock', 'price'];

    public function transaction()
    {
        return $this->hasMany(TransactionMedicines::class, 'id_foods');
    }
}
