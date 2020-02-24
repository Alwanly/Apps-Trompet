<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Medicines extends Model
{
    protected $table = 'medicines';
    protected $fillable =['name', 'image', 'stock', 'price'];

    public function transaction()
    {
        return $this->hasMany(TransactionMedicines::class, 'id_medicines');
    }
}
